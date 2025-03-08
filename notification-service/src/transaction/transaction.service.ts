import { Injectable, Logger } from '@nestjs/common';

@Injectable()
export class TransactionService {
  private readonly logger = new Logger(TransactionService.name);

  async sendSms(to: string, message: string) {
    try {
      const params = {
        Source: '',
        Destination: { ToAddresses: [to] },
        Message: {
          Subject: { Data: 'Notificación SMS' },
          Body: {
            Text: { Data: message },
          },
        },
      };

      this.logger.log(`SMS dummy enviado correctamente`);
    } catch (error) {
      this.logger.error(`Error enviando SMS: ${error.message}`);
      throw error;
    }
  }
}
