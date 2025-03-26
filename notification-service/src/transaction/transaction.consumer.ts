import { Controller, Logger } from "@nestjs/common";
import { EventPattern, Payload } from "@nestjs/microservices";
import { TransactionService } from "./transaction.service";

@Controller()
export class TransactionConsumer {

  private readonly logger = new Logger(TransactionConsumer.name);

  constructor(private readonly transactionService: TransactionService){}

  @EventPattern('login-events')
  async handleTransaction(@Payload() message: any) {
    this.logger.log(`Mensaje recibido : ${JSON.stringify(message)}`);
    this.transactionService.sendSms("573508010429", `Nuevo inicio de sesion ${message.ipAddress}`)
  }
}
