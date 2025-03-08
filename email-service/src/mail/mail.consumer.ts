import { Controller, Logger } from '@nestjs/common';
import { EventPattern, Payload } from '@nestjs/microservices';
import { MailService } from './mail.service';

@Controller()
export class MailConsumer {
  private readonly logger = new Logger(MailConsumer.name);

  constructor(private readonly mailService: MailService) {}

  @EventPattern('transaction-events')
  async handleTransaction(@Payload() message: any) {
    this.logger.log(`Mensaje recibido : ${JSON.stringify(message)}`);
    await this.mailService.sendMail(
      message.transactionEmail,
      `Transferencia: ${message.transactionType}`,
      'transaction',
      message,
    );
  }
}
