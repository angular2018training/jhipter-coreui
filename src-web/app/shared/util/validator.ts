import { NgForm } from '@angular/forms';

export class Validator {
    static isValidForm(customForm: NgForm) {
        if (customForm.invalid) {
            for (const key in customForm.form.controls) {
                if (customForm.form.controls.hasOwnProperty(key)) {
                    const control = customForm.form.controls[key];
                    control.markAsDirty();
                    control.markAsTouched();
                }
            }
            return false;
        }
        return true;
    }
}
