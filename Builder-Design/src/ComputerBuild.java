
class Computer{
    private final int cpu;
    private final int ram;

    private final int graphicCardCnt;
    private final int storageHDD;
    private final int storageSDD;

    private Computer(ComputerBuilder computerBuilder){
        this.cpu = computerBuilder.cpu;
        this.ram = computerBuilder.ram;
        this.graphicCardCnt = computerBuilder.graphicCardCnt;
        this.storageHDD = computerBuilder.storageHDD;
        this.storageSDD = computerBuilder.storageSDD;
    }

    public static class ComputerBuilder{
        private final int cpu;
        private final int ram;

        private int graphicCardCnt;
        private int storageHDD;
        private int storageSDD;

        public ComputerBuilder(int cpu, int ram){
            this.cpu = cpu;
            this.ram = ram;
        }

        public ComputerBuilder setHDD(int count){
            this.storageHDD = count;
            return this;
        }

        public ComputerBuilder setSDD(int count){
            this.storageSDD = count;
            return this;
        }

        public ComputerBuilder setGraphicCardCnt(int count){
            this.graphicCardCnt = count;
            return this;
        }

        public Computer build(){
            return new Computer(this);
        }
    }

    @Override
    public String toString() {

        return "Computer: {cpu : " + cpu + " ram: "+ ram + " graphicCardCnt: " + graphicCardCnt +  " storageHDD: " + storageHDD + " storageSDD: " + storageSDD;
    }
}


public class ComputerBuild {
    public static void main(String[] args) {
        Computer computer = new Computer.ComputerBuilder(2,2)
                .setHDD(1)
                .setGraphicCardCnt(4)
                .setSDD(552)
                .build();
        System.out.println(computer);
    }
}