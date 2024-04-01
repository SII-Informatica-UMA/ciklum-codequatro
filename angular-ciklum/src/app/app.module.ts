import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common'; // Importa CommonModule
import { AppComponent } from './app.component';
import { RutinasComponent } from './rutinas/rutinas.component'; // Importa RutinasComponent
import { EditarRutina } from './rutinas/editar-rutina/editar-rutina'; // Importa EditarRutina
import { DetallesRutina } from './rutinas/detalles-rutina/detalles-rutina';
import { CrearRutina } from './rutinas/crear-rutina/crear-rutina';
import { EjerciciosComponent } from './ejercicios/ejercicios.component'; // Importa EjerciciosComponent
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { ModalModule } from '@developer-partners/ngx-modal-dialog';
import { AppRoutingModule } from './app-routing.module';
@NgModule({
    declarations: [
        AppComponent
    ],
    
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        CommonModule,
        RutinasComponent,
        EjerciciosComponent,
        DetallesRutina,
        CrearRutina,
        EditarRutina,
        NgbModalModule,
        ModalModule
    ],
    
    providers: [],
    bootstrap: [AppComponent]
   
})
export class AppModule { }
