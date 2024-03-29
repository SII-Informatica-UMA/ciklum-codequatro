import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RutinasComponent } from "./rutinas/rutinas.component";
import { CommonModule } from '@angular/common';
import { EjercicioComponent } from './ejercicios/ejercicios.component';

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [RouterOutlet, RutinasComponent,EjercicioComponent,CommonModule]
})
export class AppComponent {
  title = 'angular-ciklum';

  mostrarRutinas: boolean = false;
  mostrarEjercicios: boolean = false;

  toggleRutinas() {
    this.mostrarRutinas = !this.mostrarRutinas;
    this.mostrarEjercicios = false;
  }

  toggleEjercicios() {
    this.mostrarRutinas = false;
    this.mostrarEjercicios = !this.mostrarEjercicios;
  }
}
