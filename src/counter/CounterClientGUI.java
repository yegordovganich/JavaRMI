package counter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.function.Supplier;

/**
 * Created by Yegor on 02.04.2017.
 */
public class CounterClientGUI extends JFrame {
    // private Counter counter;
    private JLabel counterLabel;

    public CounterClientGUI(final Counter counter) {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // this.counter = counter;
        this.counterLabel = new JLabel("0", SwingConstants.CENTER);

        final JButton incrementButton = new JButton("Plus");
        final JButton resetButton = new JButton("Reset");

        incrementButton.addActionListener(__ -> buttonClicked(uncheck(counter::increment, getCurrent(counter))));
        resetButton.addActionListener(__ -> buttonClicked(uncheck(counter::reset, getCurrent(counter))));

        this.setLayout(new GridLayout(0, 1));
        this.add(this.counterLabel);
        this.add(incrementButton);
        this.add(resetButton);
        this.setSize(300, 200);
        this.setVisible(true);
    }

    private Supplier<Integer> uncheck(SupplierUnchecked<Integer> supplier, int current) {
        return () -> {
            try {
                return supplier.get();
            } catch (RemoteException e) {
                System.out.println("Excepption: " + e.getMessage());
                return current;
            }
        };
    }

    private void buttonClicked(Supplier<Integer> fn) {
        new Thread(() -> {
                String value = fn.get().toString();
                EventQueue.invokeLater(() -> counterLabel.setText(value));
        }).start();
    }

    private int getCurrent(Counter counter) {
        try {
            return counter.get();
        } catch (RemoteException e) {
            return 0;
        }
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry localReg = LocateRegistry.getRegistry("localhost");
        Counter counter = (Counter) localReg.lookup(Counter.COUNTER_NAME);
        new CounterClientGUI(counter);
    }
}
