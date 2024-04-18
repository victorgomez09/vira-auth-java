export type Login = {
  username: string;
  password: string;
};

export type Register = {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  phone: number;
};

export type Token = {
  accessToken: string;
  accessTokenExpiration: Date;
  refreshToken: string;
  refreshTokenExpiration: Date;
};
