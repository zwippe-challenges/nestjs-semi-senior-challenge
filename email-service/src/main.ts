import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { MicroserviceOptions, Transport } from '@nestjs/microservices';
import * as dotenv from 'dotenv';
import { ConfigService } from '@nestjs/config';

dotenv.config();

async function bootstrap() {
  const app = await NestFactory.createMicroservice<MicroserviceOptions>(AppModule, {
    transport: Transport.KAFKA,
    options: {
      client: {
        brokers: [process.env.KAFKA_BROKER || "localhost:9092"]
      },
      consumer: {
        groupId: 'mail-service-consumer'
      }
    }
  });

  const configService = app.get(ConfigService);
  
  await app.listen();
  console.log("Mail service conectado a Kafka");

}
bootstrap();
