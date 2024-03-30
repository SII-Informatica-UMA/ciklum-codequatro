import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common'; // Importa CommonModule
import { AppComponent } from './app.component';
import { RutinasComponent } from './rutinas/rutinas.component'; // Importa RutinasComponent
import { EjerciciosComponent } from './ejercicios/ejercicios.component'; // Importa EjerciciosComponent
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { ModalModule } from '@developer-partners/ngx-modal-dialog';


@NgModule({
    declarations: [
        
    ],
    
    imports: [
        BrowserModule,
        FormsModule,
        CommonModule,
        RutinasComponent,
        EjerciciosComponent,
        NgbModalModule,
        ModalModule

    ],
    
    providers: [],
    bootstrap: [AppComponent]
   
})
export class AppModule { }
