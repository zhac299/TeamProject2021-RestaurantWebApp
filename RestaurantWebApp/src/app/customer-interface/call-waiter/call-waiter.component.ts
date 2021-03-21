import { Component, OnInit } from '@angular/core';
import { CustomerInterfaceComponent } from '../customer-interface.component';
import { MatSnackBar} from '@angular/material/snack-bar';
import { TableService } from '../../table.service';
import { Subscription, timer } from 'rxjs';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'call-waiter',
  templateUrl: './call-waiter.component.html',
  styleUrls: ['./call-waiter.component.sass']
})
export class CallWaiterComponent implements OnInit {

  waiterCalled: boolean;
  tableNumber: number;
  subscription: Subscription;
  refreshTimer$ = timer(0, 1000).pipe(tap(() => console.log('Fetching Tables...')));

  constructor(
    private customerInterface: CustomerInterfaceComponent,
    private tableService: TableService,
    private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.tableNumber = this.customerInterface.paramsObject.params.selectedTable;
    this.getNeedHelp();
  }

  getNeedHelp(): void {
    this.tableService.getTableByNumber(this.tableNumber).subscribe((table) => {
      this.waiterCalled = table.needsHelp;
    })
  }

  callWaiter(): void {
    this.tableService.getTableByNumber(this.tableNumber).subscribe((table) => {
      table.needsHelp = true;
      this.tableService.updateTable(table).subscribe();
    })
    this.tableService.refreshNeeded.subscribe(() => {
      this.getNeedHelp();
    })
    this.openSnackBar("A waiter will come to you","Please Wait");
  }

  cancel(): void {
    this.tableService.getTableByNumber(this.tableNumber).subscribe((table) => {
      table.needsHelp = false;
      this.tableService.updateTable(table).subscribe();
    })
    this.tableService.refreshNeeded.subscribe(() => {
      this.getNeedHelp();
    })
    this.openSnackBar("You canceled the waiter call","Still need help?");
  }

  private openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 3000,
      panelClass: ['orderSnackBar']
    });
  }
  
}