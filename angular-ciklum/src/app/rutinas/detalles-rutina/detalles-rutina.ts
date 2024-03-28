import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-detalles-rutina',
  templateUrl: './detalles-rutina.component.html',
  styleUrls: ['./detalles-rutina.component.css']
})

export class DetallesRutina {
  @Input() rutina: any;
  constructor(public activeModal: NgbActiveModal) {}
}
