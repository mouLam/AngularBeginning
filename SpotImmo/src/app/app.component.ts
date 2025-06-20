import {Component, inject} from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import {FaConfig, FaIconComponent, FaIconLibrary} from "@fortawesome/angular-fontawesome";
import {fontAwesomeIcons} from "./shared/font-awesome-icons";
import {NavbarComponent} from "./features/layout/navbar/navbar.component";
import {FooterComponent} from "./features/layout/footer/footer.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, FaIconComponent, NavbarComponent, FooterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {

  private faIconLibrary = inject(FaIconLibrary);
  private faConfig = inject(FaConfig);

  ngOnInit(): void {
    this.initFontAwesome();
  }

  private initFontAwesome() {
    this.faConfig.defaultPrefix = "far";
    this.faIconLibrary.addIcons(...fontAwesomeIcons);

  }
}
