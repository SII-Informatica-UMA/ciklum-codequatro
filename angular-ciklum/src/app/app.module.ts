import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common'; // Importa CommonModule
import { AppComponent } from './app.component';
import { RutinasComponent } from './rutinas/rutinas.component'; // Importa RutinasComponent
import { EjerciciosComponent } from './ejercicios/ejercicios.component'; // Importa EjerciciosComponent

@NgModule({
    declarations: [
        
    ],
    
    imports: [
        BrowserModule,
        CommonModule,
        RutinasComponent,
        EjerciciosComponent
    ],
    
    providers: [],
   
})
export class AppModule { }
