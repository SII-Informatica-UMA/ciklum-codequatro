import { Component, Input, OnInit } from '@angular/core';
import { Ejercicio } from '../../ejercicio';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-detalles.ejercicio',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './detalles.ejercicio.component.html',
  styleUrl: './detalles.ejercicio.component.css'
})
export class DetallesEjercicio implements OnInit{
  @Input() ejercicio!: Ejercicio;
  videoURL!: SafeResourceUrl;

  constructor(public activeModal: NgbActiveModal, private sanitizer: DomSanitizer){
  }

  ngOnInit(): void {
    this.videoURL = this.sanitizer.bypassSecurityTrustResourceUrl(this.ejercicio.multimedia[1]);
  }

}
