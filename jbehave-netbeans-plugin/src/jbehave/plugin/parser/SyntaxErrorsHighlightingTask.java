/**
 * Copyright 2013 Marcelo Busico <marcelobusico@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package jbehave.plugin.parser;

import javax.swing.text.Document;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.ParserResultTask;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;

public class SyntaxErrorsHighlightingTask extends ParserResultTask<Parser.Result>
{
    private JBehaveHintProcessor jBehaveHintProcessor;
    public SyntaxErrorsHighlightingTask()
    {
        jBehaveHintProcessor = new JBehaveHintProcessor();
    }

    @Override
    public void run(Parser.Result result, SchedulerEvent event)
    {
        Document document = result.getSnapshot().getSource().getDocument(false);
        JBehaveParserResult jBehaveParserResult = (JBehaveParserResult) result;

        jBehaveHintProcessor.processDocument(document, jBehaveParserResult);
    }

    @Override
    public int getPriority()
    {
        return 100;
    }

    @Override
    public Class<? extends Scheduler> getSchedulerClass()
    {
        return Scheduler.EDITOR_SENSITIVE_TASK_SCHEDULER;
    }

    @Override
    public void cancel()
    {
    }

}
