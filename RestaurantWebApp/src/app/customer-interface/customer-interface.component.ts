import { Component, OnInit } from '@angular/core';
import { OrderService} from '../order.service';
import { Order} from '../../models/Order';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CommonModule } from '@angular/common';  
import { MatIconModule} from '@angular/material/icon';
import { MatExpansionModule} from '@angular/material/expansion';
import { MatChipsModule} from '@angular/material/chips';

import { ExpansionPannelComponent} from './expansion-pannel/expansion-pannel.component';
import { AllergensChipsComponent} from './allergens-chips/allergens-chips.component'

interface Food {
  viewValue: string;
  mappedOrders: Order[];
  selected: boolean;
}

@Component({
  selector: 'app-customer-interface',
  templateUrl: './customer-interface.component.html',
  styleUrls: ['./customer-interface.component.sass']
})
export class CustomerInterfaceComponent implements OnInit {

  orderList: Order[] = [];
  foods: Food[] = [
    {viewValue: 'Fajitas', mappedOrders:[this.orderList[0]], selected: false},
    {viewValue: 'Nachos', mappedOrders:[this.orderList[1]], selected: false},
    {viewValue: 'Dips', mappedOrders:[this.orderList[1]], selected: false},
    {viewValue: 'Deserts', mappedOrders:[this.orderList[1]], selected: false}
  ];
  
  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
    this.orderService.getOrders().subscribe( orders => {
      this.orderList = orders;
    });
  }

  findCategory(food): void {
    for(let f of this.foods) { 
      if (food == f) {
        f.selected = true;
      } else {
        f.selected = false;
      }
    }
  }

}
