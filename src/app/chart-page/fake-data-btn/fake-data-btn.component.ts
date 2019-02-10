import { Component, OnInit, Input } from '@angular/core';

import { FakeDataService } from './fake-data.service';

@Component({
  selector: 'fake-data-btn',
  templateUrl: './fake-data-btn.component.html',
  styleUrls: ['./fake-data-btn.component.scss'],
  providers: [FakeDataService]
})
export class FakeDataBtnComponent implements OnInit {

  @Input()
  carVin: string;

  constructor(private fakeDataService: FakeDataService) { }

  ngOnInit() {
  }

  addFakeData(): void {
    this.fakeDataService.addFakeData(this.carVin);
  }

}
