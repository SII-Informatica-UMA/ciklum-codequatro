import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-editar-rutina',
  standalone: true,
  imports: [FormsModule, CommonModule],
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