import { Component, Input } from '@angular/core';
import { Ejercicio } from '../../ejercicio';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-detalles.ejercicio',
  standalone: true,
  imports: [],
  templateUrl: './detalles.ejercicio.component.html',
  styleUrl: './detalles.ejercicio.component.css'
})
export class DetallesEjercicio {
  @Input() ejercicio: any;
  constructor(public activeModal: NgbActiveModal){
  }
}
