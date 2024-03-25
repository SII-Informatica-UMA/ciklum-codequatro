import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RutinasComponent } from "./rutinas/rutinas.component";
import { EjerciciosComponent } from "./ejercicios/ejercicios.component";
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [RouterOutlet, RutinasComponent, EjerciciosComponent,CommonModule]
})
export class AppComponent {
  title = 'angular-ciklum';

  mostrarRutinas: boolean = false;
  mostrarEjercicios: boolean = false;

  toggleRutinas() {
    this.mostrarRutinas = true;
    this.mostrarEjercicios = false;
  }

  toggleEjercicios() {
    this.mostrarRutinas = false;
    this.mostrarEjercicios = true;
  }
}
