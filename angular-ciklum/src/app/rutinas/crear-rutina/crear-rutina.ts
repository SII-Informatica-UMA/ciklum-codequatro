import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-crear-rutina',
  templateUrl: './crear-rutina.component.html',
  styleUrls: ['./crear-rutina.component.css']
})

export class CrearRutina {
  nuevaRutina: any = {nombre: '', detalles: '', observaciones: ''};
  accion?: "Crear";


  constructor( public activeModal: NgbActiveModal) {}

  editarRutina(){
    this.activeModal.close(this.nuevaRutina);
  }

}