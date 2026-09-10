import { Component,ChangeDetectorRef } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  standalone:true,
  imports: [FormsModule,CommonModule],
  selector: 'app-shortener',
  styleUrl: './shortener.css',
  templateUrl: './shortener.html',
})
export class Shortener {
  loading: boolean = false;
  shortUrl: string = '';
  longUrl: string = '';
  error: string = '';

  constructor(private cdr: ChangeDetectorRef) {}

  async shortenUrl() {
    if (!this.longUrl) return;
    this.loading = true;
    this.shortUrl = '';
    this.cdr.detectChanges();

    try {
      const res = await fetch('http://localhost:8080/shorten', {
        method: 'POST',
        headers: { 'Content-Type': 'text/plain' },
        body: this.longUrl
      });
      this.shortUrl = await res.text();
    } catch {
      this.shortUrl = 'Error';
    } finally {
      this.loading = false;
      this.cdr.detectChanges();
    }
  }

  copyUrl(): void {
    if (!this.shortUrl) return;
    navigator.clipboard.writeText(this.shortUrl)
  }
}
