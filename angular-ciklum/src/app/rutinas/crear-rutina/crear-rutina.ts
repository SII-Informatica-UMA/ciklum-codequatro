import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-crear-rutina',
  standalone: true,
  imports: [FormsModule, CommonModule],
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