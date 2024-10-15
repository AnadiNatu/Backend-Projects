import { NgModule } from "@angular/core";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatChipsModule } from "@angular/material/chips";
import { MatNativeDateModule } from "@angular/material/core";
import { MatDatepickerModule } from "@angular/material/datepicker";
import { MatDialogModule } from "@angular/material/dialog";
import { MatDividerModule } from "@angular/material/divider";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatListModule } from "@angular/material/list";
import { MatMenuModule } from "@angular/material/menu";
import { MatPaginatorModule } from "@angular/material/paginator";
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { MatRadioModule } from "@angular/material/radio";
import { MatSelectModule } from "@angular/material/select";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { MatTableModule } from "@angular/material/table";
import { MatToolbarModule } from "@angular/material/toolbar";


@NgModule({
    imports: [
        MatButtonModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        MatToolbarModule,
        MatSelectModule,
        MatIconModule,
        MatProgressSpinnerModule,
        MatSnackBarModule,
        MatPaginatorModule,
        MatChipsModule,
        MatMenuModule,
        MatRadioModule,
        MatDividerModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatRadioModule,
        MatDialogModule,
        MatTableModule,
        MatListModule
    ],
    exports: [
        MatButtonModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        MatToolbarModule,
        MatSelectModule,
        MatIconModule,
        MatProgressSpinnerModule,
        MatSnackBarModule,
        MatPaginatorModule,
        MatChipsModule,
        MatMenuModule,
        MatRadioModule,
        MatDividerModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatRadioModule,
        MatDialogModule,
        MatTableModule,
        MatListModule
    ]
})
export class DemoAngularMaterialModule{}

