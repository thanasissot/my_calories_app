import { Injectable } from '@angular/core';
import {ToastrService} from "ngx-toastr";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private toastr: ToastrService) { }

  showSuccess(message: string | undefined) {
    this.toastr.success(message, "Success")
  }

  showError(message: string | undefined) {
    this.toastr.error(message, "Error")
  }

  showErrorWithTitle(message: string | undefined, title: string | undefined) {
    this.toastr.error(message, title)
  }

  showInfo(message: string | undefined) {
    this.toastr.info(message, "Info")
  }

  showWarning(message: string | undefined) {
    this.toastr.warning(message, "Warning")
  }
}
