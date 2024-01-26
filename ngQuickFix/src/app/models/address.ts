import { User } from './user';

export class Address {
  id: number;
  street: string;
  street2: string;
  city: string;
  state: string;
  zip: string;
  users: User[];

  constructor(
    id: number = 0,
    street: string = '',
    street2: string = '',
    city: string = '',
    state: string = '',
    zip: string = '',
    users: User[] = []
  ) {
    this.id = id;
    this.street = street;
    this.street2 = street2;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.users = users;
  }




}
