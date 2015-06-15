
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20150326 (SVN rev 63)
//----------------------------------------------------

package com.jetbrains.jbmake.parser;

import com.jetbrains.jbmake.parser.ast.*;

import java.util.LinkedList;
import java.util.List;

/** CUP v0.11b 20150326 (SVN rev 63) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class MakefileParser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return MakefileSymbols.class;
}

  /** Default constructor. */
  @Deprecated
  public MakefileParser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public MakefileParser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public MakefileParser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\017\000\002\002\003\000\002\002\004\000\002\003" +
    "\003\000\002\003\004\000\002\003\003\000\002\004\004" +
    "\000\002\004\005\000\002\004\004\000\002\005\004\000" +
    "\002\005\006\000\002\006\003\000\002\007\005\000\002" +
    "\007\003\000\002\010\003\000\002\011\005" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\030\000\010\003\007\005\011\011\004\001\002\000" +
    "\004\004\ufff7\001\002\000\004\002\001\001\002\000\004" +
    "\002\032\001\002\000\004\005\031\001\002\000\004\004" +
    "\022\001\002\000\004\002\ufffd\001\002\000\012\002\uffff" +
    "\003\007\005\011\011\004\001\002\000\004\005\014\001" +
    "\002\000\014\002\ufffc\003\ufffc\005\ufffc\006\015\011\ufffc" +
    "\001\002\000\004\011\017\001\002\000\012\002\ufffb\003" +
    "\ufffb\005\ufffb\011\ufffb\001\002\000\004\005\020\001\002" +
    "\000\012\002\ufff3\003\ufff3\005\ufff3\011\ufff3\001\002\000" +
    "\004\002\ufffe\001\002\000\006\005\ufff9\010\023\001\002" +
    "\000\004\011\025\001\002\000\004\005\ufff8\001\002\000" +
    "\006\005\ufff4\010\ufff4\001\002\000\006\005\ufff5\010\027" +
    "\001\002\000\004\011\025\001\002\000\004\005\ufff6\001" +
    "\002\000\012\002\ufffa\003\ufffa\005\ufffa\011\ufffa\001\002" +
    "\000\004\002\000\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\030\000\014\002\005\003\004\004\011\005\012\006" +
    "\007\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\012\003\020\004\011\005\012\006\007\001\001" +
    "\000\002\001\001\000\004\011\015\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\006\007\023\010" +
    "\025\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\006\007\027\010\025\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$MakefileParser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$MakefileParser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$MakefileParser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$MakefileParser$actions {
  private final MakefileParser parser;

  /** Constructor */
  CUP$MakefileParser$actions(MakefileParser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$MakefileParser$do_action_part00000000(
    int                        CUP$MakefileParser$act_num,
    java_cup.runtime.lr_parser CUP$MakefileParser$parser,
    java.util.Stack            CUP$MakefileParser$stack,
    int                        CUP$MakefileParser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$MakefileParser$result;

      /* select the action based on the action number */
      switch (CUP$MakefileParser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // makefile ::= rule_list 
            {
              Makefile RESULT =null;
		int llleft = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()).left;
		int llright = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()).right;
		List<Rule> ll = (List<Rule>)((java_cup.runtime.Symbol) CUP$MakefileParser$stack.peek()).value;
		 RESULT = new Makefile(ll); 
              CUP$MakefileParser$result = parser.getSymbolFactory().newSymbol("makefile",0, ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), RESULT);
            }
          return CUP$MakefileParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= makefile EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)).right;
		Makefile start_val = (Makefile)((java_cup.runtime.Symbol) CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)).value;
		RESULT = start_val;
              CUP$MakefileParser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)), ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$MakefileParser$parser.done_parsing();
          return CUP$MakefileParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // rule_list ::= rule 
            {
              List<Rule> RESULT =null;
		int rleft = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()).left;
		int rright = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()).right;
		Rule r = (Rule)((java_cup.runtime.Symbol) CUP$MakefileParser$stack.peek()).value;
		 RESULT = new LinkedList<Rule>(); RESULT.add(0, r); 
              CUP$MakefileParser$result = parser.getSymbolFactory().newSymbol("rule_list",1, ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), RESULT);
            }
          return CUP$MakefileParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // rule_list ::= rule rule_list 
            {
              List<Rule> RESULT =null;
		int rleft = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)).left;
		int rright = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)).right;
		Rule r = (Rule)((java_cup.runtime.Symbol) CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)).value;
		int rlleft = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()).left;
		int rlright = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()).right;
		List<Rule> rl = (List<Rule>)((java_cup.runtime.Symbol) CUP$MakefileParser$stack.peek()).value;
		 rl.add(0, r); RESULT = rl; 
              CUP$MakefileParser$result = parser.getSymbolFactory().newSymbol("rule_list",1, ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)), ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), RESULT);
            }
          return CUP$MakefileParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // rule_list ::= LINE_TERMINATOR 
            {
              List<Rule> RESULT =null;

              CUP$MakefileParser$result = parser.getSymbolFactory().newSymbol("rule_list",1, ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), RESULT);
            }
          return CUP$MakefileParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // rule ::= target LINE_TERMINATOR 
            {
              Rule RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)).right;
		Target t = (Target)((java_cup.runtime.Symbol) CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)).value;
		 RESULT = new Rule(t); 
              CUP$MakefileParser$result = parser.getSymbolFactory().newSymbol("rule",2, ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)), ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), RESULT);
            }
          return CUP$MakefileParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // rule ::= target LINE_TERMINATOR command 
            {
              Rule RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-2)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-2)).right;
		Target t = (Target)((java_cup.runtime.Symbol) CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-2)).value;
		int cleft = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()).left;
		int cright = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()).right;
		Command c = (Command)((java_cup.runtime.Symbol) CUP$MakefileParser$stack.peek()).value;
		 RESULT = new Rule(t, c); 
              CUP$MakefileParser$result = parser.getSymbolFactory().newSymbol("rule",2, ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-2)), ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), RESULT);
            }
          return CUP$MakefileParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // rule ::= error LINE_TERMINATOR 
            {
              Rule RESULT =null;

              CUP$MakefileParser$result = parser.getSymbolFactory().newSymbol("rule",2, ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)), ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), RESULT);
            }
          return CUP$MakefileParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // target ::= target_id COLON 
            {
              Target RESULT =null;
		int tileft = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)).left;
		int tiright = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)).right;
		TargetId ti = (TargetId)((java_cup.runtime.Symbol) CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)).value;
		 RESULT = new Target(ti); 
              CUP$MakefileParser$result = parser.getSymbolFactory().newSymbol("target",3, ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)), ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), RESULT);
            }
          return CUP$MakefileParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // target ::= target_id COLON SPACE dependency_list 
            {
              Target RESULT =null;
		int tileft = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-3)).left;
		int tiright = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-3)).right;
		TargetId ti = (TargetId)((java_cup.runtime.Symbol) CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-3)).value;
		int dlleft = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()).left;
		int dlright = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()).right;
		List<Dependency> dl = (List<Dependency>)((java_cup.runtime.Symbol) CUP$MakefileParser$stack.peek()).value;
		 RESULT = new Target(ti, dl); 
              CUP$MakefileParser$result = parser.getSymbolFactory().newSymbol("target",3, ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-3)), ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), RESULT);
            }
          return CUP$MakefileParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // target_id ::= OTHER 
            {
              TargetId RESULT =null;
		int target_id_valueleft = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()).left;
		int target_id_valueright = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()).right;
		String target_id_value = (String)((java_cup.runtime.Symbol) CUP$MakefileParser$stack.peek()).value;
		 RESULT = new TargetId(target_id_value); 
              CUP$MakefileParser$result = parser.getSymbolFactory().newSymbol("target_id",4, ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), RESULT);
            }
          return CUP$MakefileParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // dependency_list ::= dependencyName SPACE dependency_list 
            {
              List<Dependency> RESULT =null;
		int dleft = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-2)).left;
		int dright = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-2)).right;
		Dependency d = (Dependency)((java_cup.runtime.Symbol) CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-2)).value;
		int dlleft = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()).left;
		int dlright = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()).right;
		List<Dependency> dl = (List<Dependency>)((java_cup.runtime.Symbol) CUP$MakefileParser$stack.peek()).value;
		 dl.add(0, d); RESULT = dl; 
              CUP$MakefileParser$result = parser.getSymbolFactory().newSymbol("dependency_list",5, ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-2)), ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), RESULT);
            }
          return CUP$MakefileParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // dependency_list ::= dependencyName 
            {
              List<Dependency> RESULT =null;
		int dleft = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()).left;
		int dright = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()).right;
		Dependency d = (Dependency)((java_cup.runtime.Symbol) CUP$MakefileParser$stack.peek()).value;
		 RESULT = new LinkedList<Dependency>(); RESULT.add(0, d); 
              CUP$MakefileParser$result = parser.getSymbolFactory().newSymbol("dependency_list",5, ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), RESULT);
            }
          return CUP$MakefileParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // dependencyName ::= OTHER 
            {
              Dependency RESULT =null;
		int dependency_valueleft = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()).left;
		int dependency_valueright = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()).right;
		String dependency_value = (String)((java_cup.runtime.Symbol) CUP$MakefileParser$stack.peek()).value;
		 RESULT = new Dependency(dependency_value); 
              CUP$MakefileParser$result = parser.getSymbolFactory().newSymbol("dependencyName",6, ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), RESULT);
            }
          return CUP$MakefileParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // command ::= RECIPE_PREFIX OTHER LINE_TERMINATOR 
            {
              Command RESULT =null;
		int command_valueleft = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)).left;
		int command_valueright = ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)).right;
		String command_value = (String)((java_cup.runtime.Symbol) CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-1)).value;
		 RESULT = new Command(command_value); 
              CUP$MakefileParser$result = parser.getSymbolFactory().newSymbol("command",7, ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.elementAt(CUP$MakefileParser$top-2)), ((java_cup.runtime.Symbol)CUP$MakefileParser$stack.peek()), RESULT);
            }
          return CUP$MakefileParser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$MakefileParser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$MakefileParser$do_action(
    int                        CUP$MakefileParser$act_num,
    java_cup.runtime.lr_parser CUP$MakefileParser$parser,
    java.util.Stack            CUP$MakefileParser$stack,
    int                        CUP$MakefileParser$top)
    throws java.lang.Exception
    {
              return CUP$MakefileParser$do_action_part00000000(
                               CUP$MakefileParser$act_num,
                               CUP$MakefileParser$parser,
                               CUP$MakefileParser$stack,
                               CUP$MakefileParser$top);
    }
}

}
