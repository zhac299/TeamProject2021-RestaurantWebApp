import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {Menu} from '../models/Menu';
import { selectedCategory } from 'src/models/selectedCategory';
import {Order} from "../models/Order";
import {exhaustMap, map, repeat, share} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  mockDbUrl = 'http://localhost:3000/menu';
  restaurantWebApiUrl = 'http://localhost:8080/api/v1/menu';
  orderList: Menu[] = [];
  sOrder: Menu[] = [];
  cat: selectedCategory = new selectedCategory;

  private readonly menuSubject = new BehaviorSubject<Menu[]>(new Array<Menu>());
  refresh$ = new BehaviorSubject(null);

  public getMenus(): Observable<Menu[]> {
    return this.httpClient.get<Menu[]>(this.restaurantWebApiUrl);
  }
  
  menus$ = this.refresh$.pipe(
    exhaustMap( () => this.getMenus()),
    share()
  );

  private setMenus(menus: Menu[]) {
    this.menuSubject.next(menus);
  }

  constructor(private httpClient: HttpClient) {
    console.log('Instance created');
  }

  getAllUpdatedMenus(): void {
    this.httpClient.get<Menu[]>(this.restaurantWebApiUrl)
      .subscribe((menuList) => {
        this.menuSubject.next(menuList);
      });
  }

  public getMenuById(id: number): Observable<Menu>{
    return this.httpClient.get<Menu>(`${this.restaurantWebApiUrl}/${id}`);
  }

  createMenuItem(menu: Menu): void {
    this.httpClient.post<Menu>(this.restaurantWebApiUrl, menu)
      .subscribe((menu) => {
        const currentList = this.menuSubject.getValue()
        currentList.push(menu)
        this.menuSubject.next(currentList);
        this.getAllUpdatedMenus();
      });
  }

  deleteMenu(menu: Menu): void {
    this.httpClient.delete<Menu>(`${this.restaurantWebApiUrl}/${menu.id}`)
      .subscribe((menu) => {
        this.menuSubject.next(
          this.menuSubject.getValue()
            .filter(
              (ignoreMenu) =>
                ignoreMenu !== menu
            )
        );
        this.getAllUpdatedMenus();
      });
  }

  updateMenu(menu: Menu): void {
    this.httpClient.put<Menu[]>(`${this.restaurantWebApiUrl}/${menu.id}`,menu)
      .subscribe((menuList) => {
        this.menuSubject.next(menuList);
        this.getAllUpdatedMenus();
      });
  }
}
