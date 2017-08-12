package de.neemann.digital.hdl.vhdl.lib;

import de.neemann.digital.core.NodeException;
import de.neemann.digital.draw.elements.PinException;
import de.neemann.digital.draw.library.ElementNotFoundException;
import de.neemann.digital.hdl.vhdl.VHDLExporter;
import de.neemann.digital.integration.ToBreakRunner;
import junit.framework.TestCase;

import java.io.IOException;

public class VHDLFileTest extends TestCase {

    public void testSimple() throws IOException, ElementNotFoundException, PinException, NodeException {
        ToBreakRunner br = new ToBreakRunner("dig/hdl/dff.dig");
        String vhdl = new VHDLExporter(br.getLibrary()).export(br.getCircuit()).toString();
        assertEquals("-- auto generated by Digital\n" +
                "\n" +
                "LIBRARY ieee;\n" +
                "USE ieee.std_logic_1164.all;\n" +
                "USE ieee.numeric_std.all;\n" +
                "\n" +
                "entity main is\n" +
                "  port (\n" +
                "    PORT_D: in std_logic;\n" +
                "    PORT_C: in std_logic;\n" +
                "    PORT_Q: out std_logic;\n" +
                "    PORT_nQ: out std_logic;\n" +
                "    PORT_D3: in std_logic_vector (2 downto 0);\n" +
                "    PORT_Q3: out std_logic_vector (2 downto 0);\n" +
                "    PORT_nQ3: out std_logic_vector (2 downto 0) );\n" +
                "end main;\n" +
                "\n" +
                "architecture main_arch of main is\n" +
                "\n" +
                "  component DIG_D_FF\n" +
                "       port ( PORT_D  : in std_logic;\n" +
                "              PORT_C  : in std_logic;\n" +
                "              PORT_Q  : out std_logic;\n" +
                "              PORT_notQ : out std_logic );\n" +
                "  end component;\n" +
                "\n" +
                "  component DIG_D_FF_BUS\n" +
                "    generic ( bitCount : integer );\n" +
                "       port ( PORT_D  : in std_logic_vector((bitCount-1) downto 0);\n" +
                "              PORT_C  : in std_logic;\n" +
                "              PORT_Q  : out std_logic_vector((bitCount-1) downto 0);\n" +
                "              PORT_notQ : out std_logic_vector((bitCount-1) downto 0) );\n" +
                "  end component;\n" +
                "\n" +
                "begin\n" +
                "  gate0 : DIG_D_FF\n" +
                "    port map (\n" +
                "      PORT_Q => PORT_Q,\n" +
                "      PORT_notQ => PORT_nQ,\n" +
                "      PORT_D => PORT_D,\n" +
                "      PORT_C => PORT_C );\n" +
                "  gate1 : DIG_D_FF_BUS\n" +
                "    generic map (\n" +
                "      bitCount => 3 )\n" +
                "    port map (\n" +
                "      PORT_Q => PORT_Q3,\n" +
                "      PORT_notQ => PORT_nQ3,\n" +
                "      PORT_D => PORT_D3,\n" +
                "      PORT_C => PORT_C );\n" +
                "end main_arch;\n" +
                "\n" +
                "-- library components\n" +
                "\n" +
                "-- DIG_D_FF\n" +
                "\n" +
                "LIBRARY ieee;\n" +
                "USE ieee.std_logic_1164.all;\n" +
                "\n" +
                "entity DIG_D_FF is\n" +
                "     port ( PORT_D  : in std_logic;\n" +
                "            PORT_C  : in std_logic;\n" +
                "            PORT_Q  : out std_logic;\n" +
                "            PORT_notQ : out std_logic );\n" +
                "end DIG_D_FF;\n" +
                "\n" +
                "architecture DIG_D_FF_arch of DIG_D_FF is\n" +
                "\n" +
                "   signal state : std_logic;\n" +
                "\n" +
                "begin\n" +
                "   PORT_Q    <= state;\n" +
                "   PORT_notQ <= NOT( state );\n" +
                "\n" +
                "   process(PORT_C)\n" +
                "   begin\n" +
                "      if rising_edge(PORT_C) then\n" +
                "        state  <= PORT_D;\n" +
                "      end if;\n" +
                "   end process;\n" +
                "end DIG_D_FF_arch;\n" +
                "\n" +
                "-- DIG_D_FF_BUS\n" +
                "\n" +
                "LIBRARY ieee;\n" +
                "USE ieee.std_logic_1164.all;\n" +
                "\n" +
                "entity DIG_D_FF_BUS is\n" +
                "  generic ( bitCount : integer );\n" +
                "     port ( PORT_D  : in std_logic_vector((bitCount-1) downto 0);\n" +
                "            PORT_C  : in std_logic;\n" +
                "            PORT_Q  : out std_logic_vector((bitCount-1) downto 0);\n" +
                "            PORT_notQ : out std_logic_vector((bitCount-1) downto 0) );\n" +
                "end DIG_D_FF_BUS;\n" +
                "\n" +
                "architecture DIG_D_FF_BUS_arch of DIG_D_FF_BUS is\n" +
                "\n" +
                "   signal state : std_logic_vector((bitCount-1) downto 0);\n" +
                "\n" +
                "begin\n" +
                "   PORT_Q    <= state;\n" +
                "   PORT_notQ <= NOT( state );\n" +
                "\n" +
                "   process(PORT_C)\n" +
                "   begin\n" +
                "      if rising_edge(PORT_C) then\n" +
                "        state  <= PORT_D;\n" +
                "      end if;\n" +
                "   end process;\n" +
                "end DIG_D_FF_BUS_arch;\n", vhdl);
    }
}