import { Component } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DetallesRutina } from './detalles-rutina/detalles-rutina';
import { EditarRutina } from './editar-rutina/editar-rutina';

@Component({
  selector: 'app-rutinas',
  standalone: true,
  imports: [],
  templateUrl: './rutinas.component.html',
  styleUrl: './rutinas.component.css'
})
export class RutinasComponent {
  rutinas: any[] = [
    {
      nombre: 'Rutina 1',
      descripcion: 'Descripción de la rutina 1',
      observaciones: 'Observaciones de la rutina 1',
      ejercicios: ['Ejercicio 1', 'Ejercicio 2', 'Ejercicio 3']
    },
    {
      nombre: 'Rutina 2',
      descripcion: 'Descripción de la rutina 2',
      observaciones: 'Observaciones de la rutina 2',
      ejercicios: ['Ejercicio 4', 'Ejercicio 5', 'Ejercicio 6']
    }
  ]; 

  nuevaRutina: any = {
    nombre: '',
    descripcion: '',
    observaciones: '',
    ejercicios: []
  }; 

  rutinaSeleccionada: any = null; 

  editarRutinaIndex: number = -1;

  constructor(private modalService: NgbModal) {}

  agregarRutina() {
    this.rutinas.push({ ...this.nuevaRutina });
    this.nuevaRutina = {
      nombre: '',
      descripcion: '',
      observaciones: '',
      ejercicios: []
    };
  }

  mostrarDetalles(rutina: any): void {
    const modalRef = this.modalService.open(DetallesRutina);
    modalRef.componentInstance.rutina = rutina;
  }


  editarRutina(index: number) {
    this.editarRutinaIndex = index;
    this.nuevaRutina = { ...this.rutinas[index] };
  }


  actualizarRutina() {
    this.rutinas[this.editarRutinaIndex] = { ...this.nuevaRutina };
    this.editarRutinaIndex = -1;
    this.nuevaRutina = {
      nombre: '',
      descripcion: '',
      observaciones: '',
      ejercicios: []
    };
  }


  eliminarRutina(nombre: string) {
    let indice = this.rutinas.findIndex(c => c.nombre == nombre);
    this.rutinas.splice(indice, 1);
  }
}
