import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {
  transform(value: any, list: Array<any>, compareAttr: any, selectedAttr: any): any {
    const item = list.find((x) => x[compareAttr] === value);
    if (item) {
      return item[selectedAttr];
    }

    return '';
  }
}
