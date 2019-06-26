/*
 * Copyright 2019 juniocezar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lac.rangeanalysis;

import soot.PackManager;
import soot.Scene;
import soot.SootClass;
import soot.Transform;
import soot.options.Options;

/**
 *
 * @author juniocezar
 */
public class ValueRangeAnalysis {
    
    public static void main (String[] args) {
        //
        // Configure soot by setting general options
        configureSoot();
        //
        // Adding our pass to Soot Pass/Package Manager;
        LocalRangeAnalysis lra = new LocalRangeAnalysis();
        PackManager.v().getPack("stp").add(new Transform("stp.range-analysis", lra));
        //  
        // Resolving name of our external logging library        
        Scene.v().addBasicClass("java.lang.Runtime", SootClass.SIGNATURES);
        //Scene.v().loadClassAndSupport("jinn.exlib.DataLogger");
        //
        // Running Soot
        soot.Main.main(args);
  
    }


    private static void configureSoot () {
        Options.v().set_no_bodies_for_excluded(true);
        // Set via command line
        Options.v().set_whole_program(false);        
        Options.v().set_verbose(false);
        Options.v().set_src_prec(Options.src_prec_class);
        Options.v().set_output_format(Options.output_format_shimple);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_xml_attributes(false);
        Options.v().set_force_overwrite(true);
        Options.v().setPhaseOption("jb","use-original-names:true");
        Options.v().setPhaseOption("jb","preserve-source-annotations:true");
    }
      
}
