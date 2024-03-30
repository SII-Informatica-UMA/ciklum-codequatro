import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-editar-rutina',
  templateUrl: './editar-rutina.component.html',
  styleUrls: ['./editar-rutina.component.css']
})

export class EditarRutina {
  @Input() rutina: any;
  nuevaRutina: any = {nombre: '', detalles: '', observaciones: ''};
  accion?: "Añadir";


  constructor( public activeModal: NgbActiveModal) {}

  editarRutina(){
    this.activeModal.close(this.nuevaRutina);
  }

}