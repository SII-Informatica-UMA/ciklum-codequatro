import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { Ejercicio } from '../../ejercicio';
import { DetallesEjercicio } from '../../ejercicios/detalles-ejercicio/detalles.ejercicio.component';
import { AniadirEjercicioRutina } from '../aniadir-ejercicio-rutina/aniadir-ejercicio-rutina.component';
import { EjerciciosService } from '../../ejercicios.service';


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
  accion?: "Crear";


  constructor( public activeModal: NgbActiveModal, private modalService: NgbModal, private ejerciciosService: EjerciciosService) {}

  editarRutina(){
    this.activeModal.close(this.nuevaRutina);
  }

  mostrarDetallesEjercicio(ejercicio: Ejercicio): void{
    const modalRef = this.modalService.open(DetallesEjercicio);
    modalRef.componentInstance.ejercicio = ejercicio;
  }

  aniadirEjercicio(){
    const modalRef = this.modalService.open(AniadirEjercicioRutina);
    modalRef.componentInstance.accion = "Aniadir";
    modalRef.result.then((ej: {series: number; repeticiones: number; duracionMinutos: number; ejercicio: Ejercicio;}) => {
      console.log(ej);
      this.nuevaRutina.ejercicios.push(ej);
    }, (reason) => {
      console.log('No se añadió ningún ejercicio');
    });
  }

  eliminarEjercicio(ejercicio: Ejercicio){

  }
}