import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Ejercicio } from '../../ejercicio';
import { EjerciciosService } from '../../ejercicios.service';

@Component({
  selector: 'app-crear-rutina',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './crear-rutina.component.html',
  styleUrls: ['./crear-rutina.component.css']
})

export class CrearRutina implements OnInit{
  nuevaRutina: any = {nombre: '', detalles: '', observaciones: ''};
  accion?: "Crear";
  
  ejercicios!: Ejercicio[];

  constructor( public activeModal: NgbActiveModal, private ejerciciosService: EjerciciosService) {}

  ngOnInit(): void {
    this.ejercicios = this.ejerciciosService.ejercicios;
  }

  editarRutina(){
    this.activeModal.close(this.nuevaRutina);
  }

}