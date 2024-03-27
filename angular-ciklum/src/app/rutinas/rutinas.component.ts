import { Component } from '@angular/core';

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


  agregarRutina() {
    this.rutinas.push({ ...this.nuevaRutina });
    this.nuevaRutina = {
      nombre: '',
      descripcion: '',
      observaciones: '',
      ejercicios: []
    };
  }

  mostrarDetalles(rutina: any) {
    this.rutinaSeleccionada = rutina;
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


  eliminarRutina(index: number) {
    this.rutinas.splice(index, 1);
  }
}
