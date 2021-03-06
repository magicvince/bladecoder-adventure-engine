/*******************************************************************************
 * Copyright 2014 Rafael Garcia Moreno.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.bladecoder.engine.anim;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.bladecoder.engine.actions.ActionCallback;
import com.bladecoder.engine.model.SpriteActor;
import com.bladecoder.engine.util.InterpolationMode;

/**
 * Tween for spriteactor scale animation
 */
public class SpriteRotateTween extends Tween<SpriteActor> {
	
	private float startRot;
	private float targetRot;
	
	public SpriteRotateTween() {
	}

	public void start(SpriteActor target, Type repeatType, int count, float tRot, float duration, InterpolationMode interpolation, ActionCallback cb) {
		this.target = target;
		
		startRot = target.getScale();
		targetRot = tRot;
		
		setDuration(duration);
		setType(repeatType);
		setCount(count);
		setInterpolation(interpolation);

		if (cb != null) {
			setCb(cb);
		}
		
		restart();
	}

	@Override
	public void updateTarget() {
		target.setScale(startRot + getPercent() * (targetRot - startRot));
	}
	
	@Override
	public void write(Json json) {
		super.write(json);

		json.writeValue("startScl", startRot);
		json.writeValue("targetScl", targetRot);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);	
		
		startRot = json.readValue("startScl", Float.class, jsonData);
		targetRot = json.readValue("targetScl", Float.class, jsonData);
	}
}
