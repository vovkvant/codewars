package com.selfdev.chemistkata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by vovkv on 8/13/2019.
 */
class Atom implements Comparable {

    public int id;
    public String element;
    private List<Atom> relatedAtoms = new ArrayList<>();
    private int availableValency;
    private AtomEnum atomEnum;

    public Atom(String elt, int id_) {
        element = elt;
        id = id_;
        atomEnum = AtomEnum.valueOf(elt);
        availableValency = atomEnum.getValenceNumber();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void mutate(String elt) {
        AtomEnum newAtomEnum = AtomEnum.valueOf(elt);
        AtomEnum oldAtomEnum = AtomEnum.valueOf(element);

        int valencyDelta = oldAtomEnum.getValenceNumber() - availableValency;
        availableValency = newAtomEnum.getValenceNumber() - valencyDelta;
        if (availableValency < 0) {
            availableValency = newAtomEnum.getValenceNumber() + valencyDelta;
            throw new InvalidBond();
        }
        atomEnum = newAtomEnum;
        element = elt;

    }

    @Override
    public int hashCode() {
        return id;
    }        //  Do not modify this method!!

    @Override
    public boolean equals(Object other) {       //  Do not modify this method!!
        if (other != null && other instanceof Atom) {
            Atom that = (Atom) other;
            return id == that.id;
        }
        return false;
    }

    public int getAvailableValency() {
        return availableValency;
    }

    public double getAtomicWeight() {
        return atomEnum.getAtomicWeight();
    }

    public String getElement() {
        return element;
    }

    public boolean isPossibleToAdd(Atom atom) {
        return !(this.equals(atom) || availableValency <= 0);
    }

    public void addRelatedAtom(Atom atom) {
        if (this.equals(atom) || availableValency <= 0) {
            throw new InvalidBond();
        }
        availableValency--;
        relatedAtoms.add(atom);
    }

    public void removeAllHydrogens(){
        long numberOfH = relatedAtoms.stream()
                .filter(atom -> atom.getElement().equals("H")).count();
        availableValency +=numberOfH;
        relatedAtoms = relatedAtoms.stream()
                .filter(atom -> !atom.getElement().equals("H")).collect(Collectors.toList());
    }

    public List<Atom> getRelatedAtoms() {
        return relatedAtoms;
    }


    /*
    Return a string formatted like the following: "Atom(element.id: element1id,element2id,element3id...)".

element: symbol of the current Atom instance
id: id of the current element (beginning at 1 for each Molecule instance)
element1id: element1, bonded to the current Atom and its id number. If the bonded atom is a hydrogen, do not display its id number, to increase readability.
The elements bonded to the current atom must be sorted in the same order than for the raw formula, except that the hydrogens will go to the end, again for better readability. Same kind of atoms are sorted by increasing value of their id number.

Examples: "Atom(C.2: C3,C14,O6,H)" or "Atom(C.24: C1,O6,N2,H)"
     */
    @Override
    public String toString() {
        Map<Boolean, List<Atom>> groups =
                relatedAtoms.stream().collect(Collectors.partitioningBy(atom -> "H".equals(atom.getElement())));
        //need to sort list before reduce it in string
        boolean groupWithoutH = false;
        boolean Hgroup = true;
        Collections.sort(groups.get(groupWithoutH));
        List<Atom> orderedAtoms =  groups.get(groupWithoutH);
        orderedAtoms.addAll(groups.get(Hgroup));

        //Collections.sort(relatedAtoms);
        String relatedAtomsStr = orderedAtoms.stream().map(atom -> atom.element + (atom.element.equals("H") ? "" : atom.id)).collect(Collectors.joining(","));

        StringBuilder sb = new StringBuilder();
        sb.append("Atom(").append(element).append(".")
                .append(id);
        if(!relatedAtomsStr.isEmpty()) {
            sb.append(": ").append(relatedAtomsStr);
        }
        sb.append(")");

        return sb.toString();
    }

    public int getOrder() {
        return atomEnum.getOrder();
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Atom && o != null) {
            Atom anotherAtom = (Atom) o;
            if (this.atomEnum.getOrder() > anotherAtom.getOrder())
                return 1;
            if (this.atomEnum.getOrder() < anotherAtom.getOrder())
                return -1;
            if (this.id > anotherAtom.id) {
                return 1;
            }
            if (this.id < anotherAtom.id) {
                return -1;
            }
        }
        return 0;
    }

    public int compareById(Object o) {
        if (o instanceof Atom && o != null) {
            Atom anotherAtom = (Atom) o;
            if (this.id > anotherAtom.id) {
                return 1;
            }
            if (this.id < anotherAtom.id) {
                return -1;
            }
        }
        return 0;
    }

}