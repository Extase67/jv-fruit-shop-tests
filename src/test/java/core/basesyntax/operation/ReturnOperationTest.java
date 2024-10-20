package core.basesyntax.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReturnOperationTest {
    private HashMap<String, Integer> storage;

    @BeforeEach
    void setUp() {
        storage = new HashMap<>();
    }

    @Test
    void returnPositive_OK() {
        String fruit = "banana";
        int quantity = 100;
        int returnQuantity = 15;
        FruitTransaction.Operation operation = FruitTransaction.Operation.RETURN;
        storage.put(fruit, quantity);
        FruitTransaction transaction = new FruitTransaction(operation, fruit, returnQuantity);
        ReturnOperation returnOperation = new ReturnOperation();
        returnOperation.apply(storage, transaction);
        assertEquals(quantity + returnQuantity, storage.get(fruit));
    }

    @Test
    void returnZero_OK() {
        String fruit = "banana";
        int quantity = 100;
        int returnQuantity = 0;
        FruitTransaction.Operation operation = FruitTransaction.Operation.RETURN;
        storage.put(fruit, quantity);
        FruitTransaction transaction = new FruitTransaction(operation, fruit, returnQuantity);
        ReturnOperation returnOperation = new ReturnOperation();
        returnOperation.apply(storage, transaction);
        assertEquals(quantity, storage.get(fruit));
    }

    @Test
    void returnNegative_NotOK() {
        String fruit = "banana";
        int quantity = 100;
        int returnQuantity = -15;
        FruitTransaction.Operation operation = FruitTransaction.Operation.RETURN;
        storage.put(fruit, quantity);
        FruitTransaction transaction = new FruitTransaction(operation, fruit, returnQuantity);
        ReturnOperation returnOperation = new ReturnOperation();
        assertThrows(RuntimeException.class, () -> returnOperation.apply(storage, transaction));
    }
}
