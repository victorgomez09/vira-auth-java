export type Login = {
  username: string;
  password: string;
};

export type LoginResponse = {
  id: string;
  accessToken: string;
  refreshToken: string;
};

export type Register = {
  firstName: string;
  lastName: string;
  phone: number;
  username: string;
  password: string;
};
