/*
 *  Copyright 2019 EPAM Systems
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.epam.reportportal.service;

import com.epam.reportportal.listeners.ListenerParameters;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * An utility class which runs LockFile from a new application context. Used for testing LockFile service in separate processes.
 *
 * @author <a href="mailto:vadzim_hushchanskou@epam.com">Vadzim Hushchanskou</a>
 */
public class LockFileRunner {
	public static final String WELCOME_MESSAGE = "Lock ready, press any key to continue...";

	public static void main(String[] args) throws IOException {
		String lockFileName = args[0];
		String syncFileName = args[1];
		String instanceUuid = args[2];

		ListenerParameters params = new ListenerParameters();
		params.setLockFileName(lockFileName);
		params.setSyncFileName(syncFileName);
		LockFile lock = new LockFile(params);
		System.out.println(WELCOME_MESSAGE);
		InputStreamReader isr = new InputStreamReader(System.in);
		isr.read(new char[3]);
		System.out.println(lock.obtainLaunchUuid(instanceUuid));
		isr.read(new char[3]);
		lock.finishInstanceUuid(instanceUuid);
		isr.close();
	}
}
