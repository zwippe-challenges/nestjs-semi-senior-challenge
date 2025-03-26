import { Module } from '@nestjs/common';
import { TransactionService } from './transaction.service';
import { TransactionConsumer } from './transaction.consumer';

@Module({
  providers: [TransactionService],
  controllers: [TransactionConsumer],
  exports: [TransactionService]
})
export class TransactionModule {}
