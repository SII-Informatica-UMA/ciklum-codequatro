import { Component, Input } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Rutina } from '../../rutina';
import { Ejercicio } from '../../ejercicio';
import { DetallesEjercicio } from '../../ejercicios/detalles-ejercicio/detalles.ejercicio.component';

@Component({
  selector: 'app-detalles-rutina',
  templateUrl: './detalles-rutina.component.html',
  styleUrls: ['./detalles-rutina.component.css']
})

export class DetallesRutina {
  @Input() rutina!: Rutina;
  constructor(public activeModal: NgbActiveModal, private modalService: NgbModal) {}

  mostrarDetallesEjercicio(ejercicio: Ejercicio): void{
    const modalRef = this.modalService.open(DetallesEjercicio);
    modalRef.componentInstance.ejercicio = ejercicio;
  }
}
