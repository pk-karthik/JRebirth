/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.core.wave;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.jrebirth.af.core.resource.provided.JRebirthParameters;
import org.jrebirth.af.core.util.ObjectUtility;

/**
 * The class <strong>WaveTypeBase</strong>.
 *
 * @author Sébastien Bordes
 */
public final class WaveTypeBase implements WaveType {

    /** The unique identifier of the wave type. */
    private int uid;

    /** The action to performed, basically the name of the method to call. */
    private String action;

    /** Define arguments types to use. */
    private final List<WaveItem<?>> waveItemList = new ArrayList<>();

    /** The action called after, basically the name of the method to call. */
    private String returnAction;

    /** Define returned value type (if any). */
    private WaveItem<?> returnItem;

    /** The wave type of the wave returned after processing. */
    private WaveType returnWaveType;

    public static WaveType create() {
        return new WaveTypeBase();
    }

    /**
     * Default constructor.
     *
     * @param action The action to perform, "DO_" (by default see {@link JRebirthParameters.WAVE_HANDLER_PREFIX}) keyword will be prepended to the action name to generate the handler method
     *
     * @param waveItems the list of #WaveItem{@link WaveItem} required by this wave
     */
    private WaveTypeBase() {

        // Ensure that the uid will be unique at runtime
        uid(WaveTypeRegistry.getNextUid());
    }

    /**
     * Gets the uid.
     *
     * @return Returns the uid.
     */
    @Override
    public int uid() {
        return this.uid;
    }

    /**
     * Sets the uid.
     *
     * @param uid The uid to set.
     */
    @Override
    public WaveType uid(final int uid) {
        this.uid = uid;
        return this;
    }

    /**
     * Gets the action.
     *
     * @return Returns the action.
     */
    @Override
    public String action() {
        return this.action;
    }

    /**
     * Sets the uid.
     *
     * @param uid The uid to set.
     */
    @Override
    public WaveType action(final String action) {

        WaveTypeRegistry.store(action, this);

        // The action name will be used to define the name of the wave handler method
        // Prepend do the the action name to force wave handler method to begin with do (convention parameterizable)
        this.action = JRebirthParameters.WAVE_HANDLER_PREFIX.get() + action;

        return this;
    }

    /**
     * Gets the wave item list.
     *
     * @return Returns the waveItemList.
     */
    @Override
    public List<WaveItem<?>> items() {
        return this.waveItemList;
    }

    @Override
    public WaveType items(final WaveItem<?>... items) {
        // Add each wave item to manage method argument
        for (final WaveItem<?> waveItem : items) {
            this.waveItemList.add(waveItem);
        }
        return this;
    }

    /**
     * Gets the action.
     *
     * @return Returns the action.
     */
    @Override
    public String returnAction() {
        return this.returnAction;
    }

    /**
     * Sets the uid.
     *
     * @param uid The uid to set.
     */
    @Override
    public WaveType returnAction(final String returnAction) {
        this.returnAction = returnAction;
        buildReturnWaveType();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveItem<?> returnItem() {
        return this.returnItem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveType returnItem(final WaveItem<?> returnItem) {
        this.returnItem = returnItem;
        buildReturnWaveType();
        return this;
    }

    private void buildReturnWaveType() {
        if (this.returnAction != null && this.returnItem != null) {
            this.returnWaveType = WaveType.create(this.returnAction).items(returnItem());
        }
    }

    @Override
    public WaveType returnWaveType() {
        return this.returnWaveType;
    }

    /**
     * Return the required method parameter list to handle this WaveType.
     *
     * @return the parameter list (Type1 arg1, Type2 arg2 ...)
     */
    public String getItems() {
        final StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (final WaveItem<?> waveItem : items()) {
            if (first) {
                first = false;
            } else {
                sb.append(", ");
            }
            String fullName = waveItem.getItemType() instanceof ParameterizedType ? ((ParameterizedType) waveItem.getItemType()).toString()
                    : ((Class<?>) waveItem.getItemType()).getName();
            sb.append(fullName).append(" ");

            fullName = fullName.replaceAll("[<>]", "");
            if (waveItem.getName() == null || waveItem.getName().isEmpty()) {
                sb.append(ObjectUtility.lowerFirstChar(fullName.substring(fullName.lastIndexOf('.') + 1)));
            } else {
                sb.append(waveItem.getName());
            }
        }
        return sb.toString();
    }

    // /**
    // * Gets the return wave type.
    // *
    // * @return the return wave type
    // */
    // public WaveType getReturnWaveType() {
    // return this.returnWaveType;
    // }
    //
    // /**
    // * Sets the return wave type.
    // *
    // * @param returnWaveType the new return wave type
    // */
    // public void setReturnWaveType(final WaveType returnWaveType) {
    // this.returnWaveType = returnWaveType;
    // }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.action;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object waveType) {
        return waveType instanceof WaveTypeBase && uid() == ((WaveTypeBase) waveType).uid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return uid();
    }

    @Override
    public WaveType create(String action) {
        // Nothing to do yet
        return null;
    }

}
