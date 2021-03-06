package fractal.producer.io;
/*
 * copyright (c) 2003, www.pdfbox.org
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 3. Neither the name of pdfbox; nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * http://www.pdfbox.org
 *
 */
// package org.pdfbox.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

/**
 * This will write to a RandomAccessFile in the filesystem and keep track
 * of the position it is writing to and the length of the stream.
 *
 * @author Ben Litchfield (ben@csh.rit.edu)
 * @version $Revision: 1.3 $
 */
public class RandomAccessFileOutputStream extends OutputStream {
    private RandomAccessFile file;
    private long position;
    private long lengthWritten = 0;
    
    /**
     *       * Constructor to create an output stream that will write to the end of a
     *       * random access file.
     *       *
     *       * @param raf The file to write to.
     *       *
     *       * @throws IOException If there is a problem accessing the raf.
     *       */
    public RandomAccessFileOutputStream( RandomAccessFile raf ) throws IOException {
        file = raf;
        //first get the position that we will be writing to
        position = raf.length();
    }
    /**
     * This will get the position in the RAF that the stream was written
     * to.
     *
     * @return The position in the raf where the file can be obtained.
     */
    public long getPosition() {
        return position;
    }
    
    /**
     * The number of bytes written to the stream.
     *
     * @return The number of bytes read to the stream.
     */
    public long getLength() {
        long length = -1;
            length = lengthWritten;
        return length;
    }
    
    /**
     * @see OutputStream#write( byte[], int, int )
     */
    public void write( byte[] b, int offset, int length ) throws IOException {
        file.seek( position+lengthWritten );
        lengthWritten += length;
        file.write( b, offset, length );
        
    }
    /**
     * @see OutputStream#write( int )
     */
    public void write( int b ) throws IOException {
        file.seek( position+lengthWritten );
        lengthWritten++;
        file.write( b );
    }
    

}

