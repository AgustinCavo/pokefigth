package JuegoPokemon.modelo.game;


import java.util.*;


public class Mochila {

    private HashMap<Item,Integer> items;

    public Mochila(HashMap<Item,Integer> items){
        this.items = items;
    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    // Devuelve el item cuyo nombre es el ingresado. (null si el nombre es invalido)
    public Item getItem(ItemsEnum nombreItem){
        for(Map.Entry<Item, Integer> par : items.entrySet()) {
            if (par.getKey().getNombre() == nombreItem)
                return par.getKey();
        }
        return null;
    }
    // Devuelve la cantidad de items del tipo ingresado. (null si el nombre es invalido)
    public Integer getCantidadItem(ItemsEnum nombreItem){
        for(Map.Entry<Item, Integer> par : items.entrySet()) {
            if (par.getKey().getNombre() == nombreItem)
                return par.getValue();
        }
        return null;
    }

    public ArrayList<ItemsEnum> getItemsDisponibles(){
        ArrayList<ItemsEnum> itemsDisponibles = new ArrayList<>();
        for(Map.Entry<Item, Integer> par : items.entrySet()){
            if(par.getValue() > 0)
                itemsDisponibles.add(par.getKey().getNombre());
        }
        return itemsDisponibles;
    }

    // Devuelve los nombres de los items junto con sus características.
    public ArrayList<String> opcionesParaImprimir(ArrayList<ItemsEnum> nombresItems){
        ArrayList<String> opciones = new ArrayList<>();
        for (ItemsEnum nombreItem : nombresItems)
            opciones.add(nombreItem.toString() + " | " + getCantidadItem(nombreItem));
        return opciones;
    }

    //pre:-
    //post: agrega una lista de items
    public void setItems(HashMap<Item, Integer> items) {
        this.items = items;
    }


    public void quitarItem(Item itemSeleccionado){
        ItemsEnum nombre = itemSeleccionado.getNombre();
        for (Map.Entry<Item, Integer> par : items.entrySet()) {
            if (par.getKey().getNombre() == nombre){
                this.reducirCantidades(par.getKey());
                return;
            }
        }
    }

    private void reducirCantidades(Item item){
        Integer cantidadAnterior = items.get(item);
        items.put(item, cantidadAnterior-1);
    }
}

