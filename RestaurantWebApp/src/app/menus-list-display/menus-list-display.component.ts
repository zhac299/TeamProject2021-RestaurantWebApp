import {Component, OnDestroy, OnInit} from '@angular/core';
import {MenuService} from "../menu.service";
import {Menu} from "../../models/Menu";
import {MatDialog} from "@angular/material/dialog";
import {AddMenuDialogComponent} from "../waiter-menu/add-menu-dialog/add-menu-dialog.component";
import {Subscription, timer} from "rxjs";
import {tap} from "rxjs/operators";
import { Router } from '@angular/router';

@Component({
  selector: 'app-menus-list-display',
  templateUrl: './menus-list-display.component.html',
  styleUrls: ['./menus-list-display.component.sass']
})
export class MenusListDisplayComponent implements OnInit, OnDestroy {

  constructor(private menuService: MenuService, private router: Router,
              public dialog: MatDialog) { }

  menuList: Menu[] = [];
  subscription: Subscription;
  refreshTimer$ = timer(0, 1000)
    .pipe(tap());
  
  isAuth: boolean = true;

  ngOnInit(): void {
    if(this.router.url === '/client-menu') {
      this.isAuth = false;
    }
    this.subscription = this.refreshTimer$.subscribe(this.menuService.refresh$);
    this.menuService.getAllUpdatedMenus();
    this.menuService.menus$.subscribe((menu) => {
      this.menuList = menu;
      this.menuList.forEach(element => {
        this.menuService.getIngredients(element.id).subscribe(ings => {
          element.ingredientsName = "";
          ings.forEach(name => {
            element.ingredientsName +=  name.ingredient.name+", ";
          });
          element.ingredientsName = element.ingredientsName.substring(0, element.ingredientsName.length-2);
        });
      });
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  openEditMenuDialog(menu:Menu): void {

    this.menuService.getIngredients(menu.id).subscribe(ings => {
      menu.ingredients = [];
      ings.forEach(element => {
        menu.ingredients.push(element.ingredient.id);
      });
      const title = "Edit Dish";
      const dialogRef = this.dialog.open(AddMenuDialogComponent, {
        data: {menu,title},
        width: '50%'
      });

      dialogRef.afterClosed().subscribe(result => {
        if (result){
          console.log(result);
          this.menuService.update(result).subscribe();
          this.menuService.addIngredients(result.id, result.ingredients);
          this.refreshTimer$.subscribe();
        }
      });
    });

  }

  deleteMenuItem(menu: Menu) {
    this.menuService.deleteMenu(menu);
  }

  // openAddMenuDialog() {
  //   const title = "Add New Dish";
  //   let menu: Menu = new Menu();
  //   const dialogRef = this.dialog.open(AddMenuDialogComponent, {
  //     data: {menu,title},
  //     width: '50%',
  //     autoFocus: false
  //   });

  //   dialogRef.afterClosed().subscribe(menu => {
  //     if(menu){
  //       console.log(menu);
  //       this.menuService.createMenuItem(menu);
  //     }
  //   })
  // }
}
