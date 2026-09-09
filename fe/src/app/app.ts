import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Shortener } from './components/shortener/shortener';

@Component({
  imports: [RouterOutlet,Shortener],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('fe');
}
