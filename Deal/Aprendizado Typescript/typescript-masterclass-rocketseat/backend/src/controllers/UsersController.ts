import { Request, Response } from 'express';
import EmailService from '../services/EmailService';

const users = [
  { name: 'Rafael', email: 'rafael@email.com.br'}
];

export default {

  async index(req: Request, res: Response) {
    return res.json(users);
  },

  async create(req: Request, res: Response) {
    const emailService = new EmailService();

    const { name, email } = req.body;

    users.push( {name, email} );

    emailService.sendMail(
      {
        to: {name, email},
        message: {subject: 'Bem vindo!', body: 'Seja bem vindo!'}
      }
    );

    return res.send();
  }

}