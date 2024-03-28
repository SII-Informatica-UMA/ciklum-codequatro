import { Component } from '@angular/core';

@Component({
  selector: 'app-ejercicios',
  standalone: true,
  imports: [],
  templateUrl: './ejercicios.component.html',
  styleUrl: './ejercicios.component.css'
})
export class EjerciciosComponent {
   mostrarInformacion(nombreEjercicio : String) : void {
    alert('Información del ejercicio: ' + nombreEjercicio + '\n\n' +
          '"Nombre": "{{nombreEjercicio.nombre}}",\n' +
          '"Descripción": "string",\n' +
          '"Observaciones": "string",\n' +
          '"Tipo": "string",\n' +
          '"Músculos Trabajados": "string",\n' +
          '"Material": "string",\n' +
          '"Dificultad": "string",\n' +
          '"Multimedia": ["string"],\n' +
          '"ID": 0');
  }
}
