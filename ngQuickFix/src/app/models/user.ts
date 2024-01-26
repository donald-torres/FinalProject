import { Address } from "./address";

export class User {
  id: number;
  username: string;
  password: string;
  enabled: boolean;
  role: string;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  imageUrl: string;
  createDate: string;
  updateDate: string;
  address: Address;

  constructor(
    id: number = 0,
    username: string = '',
    password: string = '',
    enabled: boolean = true,
    role: string = '',
    firstName: string = '',
    lastName: string = '',
    email: string = '',
    phone: string = '',
    imageUrl: string = '',
    createDate: string = '',
    updateDate: string = '',
    address: Address = new Address()
  ) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.role = role;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.imageUrl = imageUrl;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.address = address;
  }



}
