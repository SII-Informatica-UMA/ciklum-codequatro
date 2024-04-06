import { CommonModule } from "@angular/common";
import { Component, Input } from "@angular/core";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";
import { FormsModule } from "@angular/forms";
import { Ejercicio } from "../../ejercicio";

@Component({
    selector: 'app-editar-ejercicio',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './editar-ejercicio.component.html',
    styleUrl: './editar-ejercicio.component.css'
})
export class EditarEjercicio {
    @Input() ejercicio!: Ejercicio;
    
    accion?: "Añadir";

    constructor(public activeModal: NgbActiveModal) {}
    
    editarEjercicio() {
        this.activeModal.close(this.ejercicio);
    }

    camposCompletos(): boolean {
        return this.ejercicio.nombre && this.ejercicio.descripcion !== '';
    }
}