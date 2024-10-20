package core.basesyntax.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SupplyOperationTest {
    private HashMap<String, Integer> storage;

    @BeforeEach
    void setUp() {
        storage = new HashMap<>();
    }

    @Test
    void supplyPositive_OK() {
        String fruit = "banana";
        int quantity = 100;
        int supplyQuantity = 100;
        FruitTransaction.Operation operation = FruitTransaction.Operation.SUPPLY;
        storage.put(fruit, quantity);
        FruitTransaction transaction = new FruitTransaction(operation, fruit, quantity);
        SupplyOperation supplyOperation = new SupplyOperation();
        supplyOperation.apply(storage, transaction);
        assertEquals(quantity + supplyQuantity, storage.get(fruit));
    }

    @Test
    void supplyNonExistent_OK() {
        String fruit = "banana";
        int quantity = 100;
        FruitTransaction.Operation operation = FruitTransaction.Operation.SUPPLY;
        FruitTransaction transaction = new FruitTransaction(operation, fruit, quantity);
        SupplyOperation supplyOperation = new SupplyOperation();
        supplyOperation.apply(storage, transaction);
        assertEquals(quantity, storage.get(fruit));
    }

    @Test
    void supplyZero_OK() {
        String fruit = "banana";
        int quantity = 0;
        FruitTransaction.Operation operation = FruitTransaction.Operation.SUPPLY;
        FruitTransaction transaction = new FruitTransaction(operation, fruit, quantity);
        SupplyOperation supplyOperation = new SupplyOperation();
        supplyOperation.apply(storage, transaction);
        assertEquals(quantity, storage.get(fruit));
    }

    @Test
    void supplyNegative_NotOK() {
        String fruit = "banana";
        int quantity = -100;
        FruitTransaction.Operation operation = FruitTransaction.Operation.SUPPLY;
        FruitTransaction transaction = new FruitTransaction(operation, fruit, quantity);
        SupplyOperation supplyOperation = new SupplyOperation();
        assertThrows(RuntimeException.class, () -> supplyOperation.apply(storage, transaction));
    }
}
