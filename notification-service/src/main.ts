import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';

import * as dotenv from 'dotenv';
import { MicroserviceOptions, Transport } from '@nestjs/microservices';

dotenv.config();
async function bootstrap() {
  const app = await NestFactory.createMicroservice<MicroserviceOptions>(AppModule, {
    transport: Transport.KAFKA,
    options: {
      client: {
        brokers: [process.env.KAFKA_BROKER || "localhost:9092"]
      },
      consumer: {
        groupId: 'notification-service-consumer'
      }
    }
  });

  await app.listen();
  console.log("Notification service conectado a Kafka");

}
bootstrap();
