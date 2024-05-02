import { Component, OnInit, WritableSignal, signal } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { DividerModule } from 'primeng/divider';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { ListboxClickEvent, ListboxModule } from 'primeng/listbox';
import { HeaderComponent } from '../../../components/header/header.component';
import { Space } from '../../../models/docs.model';
import { userStore } from '../../../shared/stores/user.store';
import { DocService } from '../../../services/doc.service';

interface ISpaceForm {
  name: FormControl<string | null>;
  description: FormControl<string | null>;
  code: FormControl<string | null>;
}

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    ListboxModule,
    DividerModule,
    ButtonModule,
    DialogModule,
    InputTextModule,
    InputTextareaModule,
    ReactiveFormsModule,
    HeaderComponent,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit {
  public loading: boolean;
  public visible: boolean;
  public spaceForm: FormGroup;
  public user;
  public spaces: WritableSignal<Space[]>;

  constructor(
    private fb: FormBuilder,
    private service: DocService,
  ) {
    this.spaceForm = this.fb.group<ISpaceForm>({
      name: this.fb.control('', Validators.required),
      description: this.fb.control(''),
      code: this.fb.control('', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(3),
      ]),
    });

    this.loading = false;
    this.visible = false;
    this.user = userStore;
    this.spaces = this.service.spaces;
  }

  ngOnInit(): void {
    this.service.getAllSpacesFromUser();
    // this.spaces.set([
    //   {
    //     id: 1,
    //     name: 'Space 1',
    //     description: 'First space',
    //     code: 'SP1',
    //     users: [],
    //   },
    //   {
    //     id: 2,
    //     name: 'Space 2',
    //     description: 'Second space',
    //     code: 'SP2',
    //     users: [],
    //   },
    // ]);
  }

  showDialog() {
    this.visible = true;
  }

  handleLoginSubmit() {}

  handleClickOption(event: ListboxClickEvent) {
    console.log('test', event.option);
  }
}
